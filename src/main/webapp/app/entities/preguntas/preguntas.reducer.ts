import axios from 'axios';
import { createAsyncThunk, isFulfilled, isPending, isRejected } from '@reduxjs/toolkit';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { IQueryParams, createEntitySlice, EntityState, serializeAxiosError } from 'app/shared/reducers/reducer.utils';
import { IPreguntas, defaultValue } from 'app/shared/model/preguntas.model';

const initialState: EntityState<IPreguntas> = {
  loading: false,
  errorMessage: null,
  entities: [],
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

const apiUrl = 'api/preguntas';

// Actions

export const getEntities = createAsyncThunk('preguntas/fetch_entity_list', async ({ page, size, sort, query }: IQueryParams) => {
  const q = [];
  if (sort) q.push(`page=${page}&size=${size}&sort=${sort}`);
  if (query) q.push(query);
  const requestUrl = `${apiUrl}${sort ? `?${q.join('&')}&` : '?'}cacheBuster=${new Date().getTime()}`;
  return axios.get<IPreguntas[]>(requestUrl);
});

export const getTest = createAsyncThunk('preguntas/fetch_test', async () => {
  const requestUrl = `${apiUrl}/test`;
  return axios.get<IPreguntas[]>(requestUrl);
});

export const getEntity = createAsyncThunk(
  'preguntas/fetch_entity',
  async (id: string | number) => {
    const requestUrl = `${apiUrl}/${id}`;
    return axios.get<IPreguntas>(requestUrl);
  },
  { serializeError: serializeAxiosError }
);

export const createEntity = createAsyncThunk(
  'preguntas/create_entity',
  async (entity: IPreguntas, thunkAPI) => {
    const result = await axios.post<IPreguntas>(apiUrl, cleanEntity(entity));
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError }
);

export const updateEntity = createAsyncThunk(
  'preguntas/update_entity',
  async (entity: IPreguntas, thunkAPI) => {
    const result = await axios.put<IPreguntas>(`${apiUrl}/${entity.nro}`, cleanEntity(entity));
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError }
);

export const partialUpdateEntity = createAsyncThunk(
  'preguntas/partial_update_entity',
  async (entity: IPreguntas, thunkAPI) => {
    const result = await axios.patch<IPreguntas>(`${apiUrl}/${entity.nro}`, cleanEntity(entity));
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError }
);

export const deleteEntity = createAsyncThunk(
  'preguntas/delete_entity',
  async (id: string | number, thunkAPI) => {
    const requestUrl = `${apiUrl}/${id}`;
    const result = await axios.delete<IPreguntas>(requestUrl);
    thunkAPI.dispatch(getEntities({}));
    return result;
  },
  { serializeError: serializeAxiosError }
);

// slice

export const PreguntasSlice = createEntitySlice({
  name: 'preguntas',
  initialState,
  extraReducers(builder) {
    builder
      .addCase(getEntity.fulfilled, (state, action) => {
        state.loading = false;
        state.entity = action.payload.data;
      })
      .addCase(deleteEntity.fulfilled, state => {
        state.updating = false;
        state.updateSuccess = true;
        state.entity = {};
      })
      .addMatcher(isFulfilled(getEntities), (state, action) => {
        const { data, headers } = action.payload;

        return {
          ...state,
          loading: false,
          entities: data,
          totalItems: parseInt(headers['x-total-count'], 10),
        };
      })
      .addMatcher(isFulfilled(createEntity, updateEntity, partialUpdateEntity), (state, action) => {
        state.updating = false;
        state.loading = false;
        state.updateSuccess = true;
        state.entity = action.payload.data;
      })
      .addMatcher(isPending(getEntities, getEntity), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.loading = true;
      })
      .addMatcher(isPending(createEntity, updateEntity, partialUpdateEntity, deleteEntity), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.updating = true;
      });
  },
});

export const { reset } = PreguntasSlice.actions;

// Reducer
export default PreguntasSlice.reducer;
