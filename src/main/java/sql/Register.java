package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.DBconfig;


public class Register {
	public void customer_register(int admin_id, String name, String address) throws FileNotFoundException {
		
		//データベースへの接続情報をプロパティファイルから取得
		DBconfig db_info = new DBconfig();
		String url = db_info.getDBinfo().get("url");
		String user = db_info.getDBinfo().get("user");
		String pass = db_info.getDBinfo().get("password");
		
		//実行SQL
		String register_sql = "insert into customer_tb"
				+ "(admin_id, name, address) values(?,?,?)";
		
		//データベースへの接続
		//try～catch～resources～構文を使用
			try(Connection conn = DriverManager.getConnection(url, user, pass)){
				//オートコミット機能を無効か
					conn.setAutoCommit(false);
					
					try(PreparedStatement stmt = conn.prepareStatement(register_sql)){
						//変数register_sqlの一番目の?にdmin_idをセット
						stmt.setInt(1, admin_id);
						//変数register_sqlの一番目の?にnameをセット
						stmt.setString(2,  name);
						//変数register_sqlの一番目の?にaddressをセット
						stmt.setString(3,  address);
						//SQLの実行
						stmt.executeUpdate();
						
						//コミット
						conn.commit();
						System.out.println("コミット処理を行いました");
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
	
	

