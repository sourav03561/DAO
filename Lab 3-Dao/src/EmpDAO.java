import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class EmpDAO extends DAO<Emp>{
    public EmpDAO(Connection connection)
    {
        super(connection);
    }
    public  Emp find(int Empno)
    {
        Emp emp01 = new Emp();
        try{
            PreparedStatement findDept =
                    connect.prepareStatement("select * from Emp where Empno=?");
            findDept.setInt(1,Empno);
            ResultSet result = findDept.executeQuery();
            while (result.next())
            {
                emp01.setEmpNo((long) result.getInt("Empno"));
                emp01.setEname(result.getString("ename"));
                emp01.setEfirst(result.getString("efirst"));
                emp01.setJob(result.getString("job"));
                Emp manager = find(result.getInt("mgr"));
                emp01.setMgr(manager);
                emp01.setHireDate(result.getDate("hiredate"));
                emp01.setSal(result.getInt("sal"));
                emp01.setComm(result.getInt("comm"));
                emp01.setTel(Integer.parseInt(result.getString("tel")));
                DeptDAO deptDAO = new DeptDAO(connect);
                Dept department = deptDAO.find(result.getInt("deptno"));
                emp01.setDepartment(department);
            }
            result.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return emp01;
    }
    @Override
    public boolean create(Emp object) {
        return false;
    }

    @Override
    public boolean update(Emp object) {
        return false;
    }

    @Override
    public boolean delete(Emp object) {
        return false;
    }
}
