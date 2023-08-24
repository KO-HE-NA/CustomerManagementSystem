package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.DBconfig;

public class Delete {
	public void customer_delete(int customer_id) throws FileNotFoundException {
		
		//データベースへの接続情報をプロパティファイルから取得
		DBconfig db_info = new DBconfig();
		String url = db_info.getDBinfo().get("url");
		String user = db_info.getDBinfo().get("user");
		String pass = db_info.getDBinfo().get("password");
		
		//実行SQL
		String delete_sql = "delete from customer_tb where customer_id = ?;";
		
		//データベースへの接続
		//try～catch～resources構文を使用
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			//オートコミット機能を無効か
			conn.setAutoCommit(false);
			
			try(PreparedStatement stmt = conn.prepareStatement(delete_sql)){
				//変数delete_sqlの１番目の?にcustomer_idをセット
				stmt.setInt(1,  customer_id);
				//SQLの実行
				stmt.executeUpdate();
				//コミット
				conn.commit();
				System.out.println("削除処理が成功しました");
			} catch (SQLException e) {
				conn.rollback();
				System.out.println("ロールバック処理を行いました");
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
