import com.trilogy.DateCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DateCalulatorTest {
    private DateCalculator dateCalculator;

    @Before
    public void instantiateDateCalculator() {
        dateCalculator = new DateCalculator();
    }

    @Test
    public void june2019Has30Days(){
        DateCalculator dateCalculator = new DateCalculator();
        int days = dateCalculator.calculateNumberofDaysInAMonth(2019, 6);

        Assert.assertEquals(days, 30);
    }

    @Test
    public void february2019Has28Days(){
        DateCalculator dateCalculator = new DateCalculator();
        int days = dateCalculator.calculateNumberofDaysInAMonth(2019,2);

        Assert.assertEquals(days, 28);
    }

    @Test
    public void leapYearWhereFebruaryHas29Days(){
        DateCalculator dateCalculator = new DateCalculator();
        int days = dateCalculator.calculateLeapYear(2020);

        Assert.assertEquals(days,29 );
    }


}
