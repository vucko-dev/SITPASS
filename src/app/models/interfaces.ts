export interface Discipline {
    id:number;
    name: string;
}

export interface WorkDay {
    id:number;
    validFrom:number[];
    dayOfWeek:string;
    from:number[];
    until:number[];
}

export interface Comment {
    id: number;
    text: string;
    userId: number;
    createdAt: Date;
    replies?: Comment[];  
  }
  