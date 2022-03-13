export interface ICategorias {
  codigo?: number | null;
  tipo?: string | null;
  descrip?: string | null;
}

export const defaultValue: Readonly<ICategorias> = {};
