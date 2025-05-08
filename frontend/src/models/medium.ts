export enum MediumStatus {
  VERFUEGBAR = 'VERFUEGBAR',
  AUSGELIEHEN = 'AUSGELIEHEN',
  RESERVIERT = 'RESERVIERT',
}
export class Medium {
  mediumId: number;
  titel: string;
  autor: string;
  kategorie: string;
  status: MediumStatus;

  constructor({ mediumId, titel, autor, kategorie, status }: Medium) {
    this.mediumId = mediumId;
    this.titel = titel;
    this.autor = autor;
    this.kategorie = kategorie;
    this.status = status;
  }
}
