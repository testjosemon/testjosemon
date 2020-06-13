import{attendedExam} from './attendedExam';
export interface dashboard
{
    currentUser:Object;
    userId:string;
    attendedExamList:attendedExam[];
}