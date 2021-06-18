export class BugModel {
  constructor(
    public id: number,
    public solutionDTOList: { [key: string]: string; },
    public descriptionDTO: []
  ) {
  }
}
