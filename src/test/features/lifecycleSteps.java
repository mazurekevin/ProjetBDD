import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

public class LifeCycleSteps {

    private ScenarioContext context;

    public LifeCycleSteps(ScenarioContext context) {
        this.context = context;
    }

    @ParameterType(".*")
    public Cell cell(){
        return Cell;
    }

    @Given("A living {cell} has two or three living neighbours")
    public void livingCellWithTwoOrThreeNeighbours(CellMap cellmap) {
        cellmap.setInfectionLevel(0);
    }

    @Given("{city} has been infected {int} time(s)")
    public void parisHasBeenInfectedTime(City city, int infectionLevel) {
        city.setInfectionLevel(infectionLevel);
    }

    @When("{city} is infected")
    public void cityIsInfected(City city) {
        city.infect();
    }

    @Then("{city}' infection level should be/remain {int}")
    public void parisInfectionLevelShouldBe(City city, int infectionLevel) {
        Assertions.assertThat(city.getInfectionLevel()).isEqualTo(infectionLevel);
    }
}
