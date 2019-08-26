package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
    	Project projectA = new Project(ProjectType.INTERNAL, "test");
        // when
    	ExpenseType resultA = ExpenseService.getExpenseCodeByProjectTypeAndName(projectA);
        // then
    	Assertions.assertEquals(ExpenseType.INTERNAL_PROJECT_EXPENSE,resultA);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
    	Project projectB = new Project(ProjectType.EXTERNAL, "Project A");
        // when
    	ExpenseType resultB = ExpenseService.getExpenseCodeByProjectTypeAndName(projectB);
        // then
    	Assertions.assertEquals(ExpenseType.EXPENSE_TYPE_A,resultB);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        // given
    	Project projectC = new Project(ProjectType.EXTERNAL, "Project B");
        // when
    	ExpenseType resultC = ExpenseService.getExpenseCodeByProjectTypeAndName(projectC);
        // then
    	Assertions.assertEquals(ExpenseType.EXPENSE_TYPE_B,resultC);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
    	Project projectD = new Project(ProjectType.EXTERNAL, "Project C");
        // when
    	ExpenseType resultD = ExpenseService.getExpenseCodeByProjectTypeAndName(projectD);
        // then
    	Assertions.assertEquals(ExpenseType.OTHER_EXPENSE,resultD);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
        // given
    	Project projectE = new Project(ProjectType.UNEXPECTED_PROJECT_TYPE, "Project E");

        // when 
    	UnexpectedProjectTypeException exception = Assertions.assertThrows(UnexpectedProjectTypeException.class,()->{
    		ExpenseService.getExpenseCodeByProjectTypeAndName(projectE);
    	});
        // then
    	Assertions.assertEquals("You enter invalid project type",exception.getMessage());
    }
}