import { IPreguntas } from "./preguntas.model";

export interface IFotos {
  nro?: number | null;
  foto?: string | null;
  foto1?: string | null;
  foto2?: string | null;
  foto3?: string | null;
  preguntas?: IPreguntas[] | null;
}

export const defaultValue: Readonly<IFotos> = {};
