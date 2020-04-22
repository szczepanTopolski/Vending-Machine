import view.View;

public class Controller {
    View view;
    DataAccess dataAccess;

    Controller(View view, DataAccess dataAccess){
        this.dataAccess = dataAccess;
        this.view = view;
    }
    public static void main(String[] args) {

    }


}
