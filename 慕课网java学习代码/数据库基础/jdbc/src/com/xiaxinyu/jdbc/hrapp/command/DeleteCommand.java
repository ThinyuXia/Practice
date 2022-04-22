package com.xiaxinyu.jdbc.hrapp.command;

import com.xiaxinyu.jdbc.common.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 删除员工数据
 */
public class DeleteCommand implements Command{
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入员工编号");
        Integer eno = in.nextInt();
        String sql = "DELETE FROM employee WHERE eno = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setFloat(1,eno);
            int cnt = pstmt.executeUpdate();
            if(cnt == 1) System.out.println("员工离职手续已完成");
             else System.out.println("未找到" + eno + "编号员工数据");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection(null,pstmt,conn);
        }

    }
}
