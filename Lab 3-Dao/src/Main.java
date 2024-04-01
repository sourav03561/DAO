import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            Class.forName( "org.postgresql.Driver" );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
        String url = "jdbc:postgresql://localhost/postgres";
        String user = "postgres";
        String pass = "Naren@03561";
        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection( url, user, pass );

            /* Requests to bdd will be here */
            System.out.println("Bdd Connected");
            /*DAO<Dept> departmentDao = new DeptDAO(connexion);
            Dept dept20 = departmentDao.find(20);
            System.out.println(dept20);
            DAO<Emp> EmloyeeDao = new EmpDAO(connexion);
            Emp emp20 = EmloyeeDao.find(7369);
            System.out.println(emp20);*/
            DAOFactory factory = new JDBCDAOFactory(connexion);

            // Use EmpDAO
            EmpDAO empDAO = factory.getEmpDAO();
            Emp emp = empDAO.find(7369); // Example usage
            System.out.println(emp);
            // Use DeptDAO
            DeptDAO deptDAO = factory.getDeptDAO();
            Dept dept = deptDAO.find(20);
            System.out.println(dept);

            DependentsDAO dependentsDAO = factory.getDependentsDAO();
            Dependents dependents = dependentsDAO.find(8000);
            System.out.println(dependents);

            BonusDAO bonusDAO = factory.getBonusDAO();
            Bonus bonus = bonusDAO.find(5000);
            System.out.println(bonus);
            /*displayDepartment(connexion);*/
            /*Scanner scanner = new Scanner(System.in);
            System.out.print("Enter employee number: ");
            int empno = scanner.nextInt();
            System.out.print("Enter new department number: ");
            int newDeptno = scanner.nextInt();
            moveDepartment(connexion,empno,newDeptno);
            displayTable(connexion,"emp");*/

        } catch ( SQLException e ) {
            e.printStackTrace();
        } finally {
            if ( connexion != null )
                try {
                    connexion.close();
                } catch ( SQLException ignore ) {
                    ignore.printStackTrace();
                }
        }
    }
}