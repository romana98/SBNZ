import {SolutionModel} from "./solution-model";
import {DescriptionModel} from "./description-model";

export class ProblemModel {
  constructor(
    public currentSolution: SolutionModel,
    public triedSolutions: SolutionModel[],
    public problems: DescriptionModel[],
    public solutionWorked: boolean
  ) {
  }
}
