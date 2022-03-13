import { ITematicas } from 'app/shared/model/tematicas.model';
import { IArtinc } from 'app/shared/model/artinc.model';
import { IFotos } from './fotos.model';

export interface IPreguntas {
  nro?: number | null;
  texto?: string | null;
  resp1?: string | null;
  resp2?: string | null;
  resp3?: string | null;
  correcta?: number | null;
  usada?: boolean | null;
  foto?: IFotos | null;
  tematica?: ITematicas | null;
  artinc?: IArtinc | null;
}

export const defaultValue: Readonly<IPreguntas> = {
  usada: false,
};
