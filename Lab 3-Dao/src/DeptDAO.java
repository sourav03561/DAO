import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptDAO extends DAO<Dept>{
    public DeptDAO(Connection connection)
    {
        super(connection);
    }
    public  Dept find(int Deptno)
    {
        Dept dept01 = new Dept();
        try{
            PreparedStatement findDept =
                    connect.prepareStatement("select * from dept where Deptno=?");
            findDept.setInt(1,Deptno);
            ResultSet result = findDept.executeQuery();
            while (result.next())
            {
                dept01.setDeptno(result.getInt("deptno"));
                dept01.setDname(result.getString("dname"));
                dept01.setLoc(result.getString("loc"));
            }
            result.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return dept01;
    }

    @Override
    public boolean create(Dept object) {
        return false;
    }

    @Override
    public boolean update(Dept object) {
        return false;
    }

    @Override
    public boolean delete(Dept object) {
        return false;
    }
}
