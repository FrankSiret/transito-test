import { IPreguntas } from 'app/shared/model/preguntas.model';

export interface ITematicas {
  id?: number | null;
  descrip?: string | null;
  cantidad?: number | null;
  preguntas?: IPreguntas[] | null;
}

export const defaultValue: Readonly<ITematicas> = {};
