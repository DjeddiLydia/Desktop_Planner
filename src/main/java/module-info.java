module com.example.my_desktop_planner {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.my_desktop_planner to javafx.fxml;
    exports com.example.my_desktop_planner;
    exports com.example.my_desktop_planner.Taches_Prj;
    opens com.example.my_desktop_planner.Taches_Prj to javafx.fxml;
    exports com.example.my_desktop_planner.Gestion;
    opens com.example.my_desktop_planner.Gestion to javafx.fxml;
}