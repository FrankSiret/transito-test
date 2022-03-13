import { IPreguntas } from 'app/shared/model/preguntas.model';

export interface IArtinc {
  artinc?: number | null;
  pelig?: string | null;
  descrip?: string | null;
  preguntas?: IPreguntas[] | null;
}

export const defaultValue: Readonly<IArtinc> = {};
