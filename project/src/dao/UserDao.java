package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import model.User;

/**
 * ユーザテーブル用のDao
 * @author takano
 *
 */
public class UserDao {

    /**
     * ログインIDとパスワードに紐づくユーザ情報を返す
     * @param loginId
     * @param password
     * @return
     * @return
     * @throws NoSuchAlgorithmException
     */
    public  User findByLoginInfo(String loginId, String password) throws NoSuchAlgorithmException {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

	    		//パスワード暗号化
	    		String source = password;
	    		Charset charset = StandardCharsets.UTF_8;
	    		String algorithm = "MD5";
	    		byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
	    		String result = DatatypeConverter.printHexBinary(bytes);

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.setString(2, result);
            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }

            String loginIdData = rs.getString("login_id");
            String nameData = rs.getString("name");
            return new User(loginIdData, nameData);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }


    /**
     * 全てのユーザ情報を取得する
     * @return
     */
    public List<User> findAll() {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE id > 1";

             // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return userList;
    }

    /**
     * 検索機能
     * @return
     */
    public List<User> findAllSerch(String loginid, String serchName, String date1, String date2) {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            // 検索内容
            String serch;

            if(!loginid.equals("") && serchName.equals("") && date1.equals("") && date2.equals("")) {
            		//loginIdのみ検索
            		serch = "login_id = '" + loginid + "';";
            }else if(!loginid.equals("") && !serchName.equals("") && date1.equals("") && date2.equals("")) {
            		//loginidとnameのみ検索
            		serch = "login_id = '" + loginid + "' AND name LIKE '%" + serchName + "%';";
            }else if(!loginid.equals("") && !serchName.equals("") && !date1.equals("") && date2.equals("")) {
            		//loginidとnameとdate1で検索
            		serch = "login_id = '" + loginid + "' AND name LIKE '%" + serchName + "%' AND birth_date >= "+ date1 + "';";
            }else if(loginid.equals("") && !serchName.equals("") && date1.equals("") && date2.equals("")) {
            		//nameのみ検索
            		serch = "name LIKE '%" + serchName + "%';";
            }else if(loginid.equals("") && !serchName.equals("") && !date1.equals("") && date2.equals("")) {
            		//nameとdate1のみ検索
            		serch = "name LIKE '%" + serchName + "%' AND birth_date >= '" + date1 + "';";
            }else if(loginid.equals("") && !serchName.equals("") && !date1.equals("") && !date2.equals("")) {
            		//nameとdate1とdate2のみ検索
            		serch = "name LIKE '%" + serchName + "%' AND birth_date BETWEEN '" + date1 + "' AND '" + date2 + "';";
            }else if(loginid.equals("") && serchName.equals("") && !date1.equals("") && date2.equals("")) {
            		//date1のみ検索
            		serch = "birth_date >= '" + date1 + "';";
            }else if(loginid.equals("") && serchName.equals("") && !date1.equals("") && !date2.equals("")) {
            		//date1とdate2のみ検索
            		serch = "birth_date BETWEEN '" + date1 + "' AND '" + date2 + "';";
            }else if(loginid.equals("") && serchName.equals("") && date1.equals("") && !date2.equals("")) {
            		//date2のみ検索
            		serch = "birth_date <= '" + date2 + "';";
            }else {
            		//全ての項目で検索
            		serch = "login_id = '" + loginid + "' AND name LIKE '%" + serchName + "%' AND birth_date BETWEEN '" + date1 + "' AND '" + date2 + "';";
            }

            String sql = "SELECT * FROM user WHERE id > 1 AND " + serch;

             // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return userList;
    }


    /**
     * ユーザ新規登録
     * @throws NoSuchAlgorithmException
     */
    public void createUser(String loginId, String password, String name, String birthDate) throws NoSuchAlgorithmException {
        Connection conn = null;

        try {
            // データベースへ接続
        		conn = DBManager.getConnection();

        		//パスワード暗号化
        		String source = password;
        		Charset charset = StandardCharsets.UTF_8;
        		String algorithm = "MD5";
        		byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
        		String result = DatatypeConverter.printHexBinary(bytes);

            // SELECT文を準備
            String sql = "INSERT INTO user(login_id, name, birth_date, password, create_date, update_date) VALUES(?,?,?,?,now(),now());";

           /* LocalDateTime d = LocalDateTime.now();
    			DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    			String date = df1.format(d);*/

            // SELECTを実行
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.setString(2, name);
            pStmt.setString(3, birthDate);
            pStmt.setString(4, result);
//            pStmt.setString(5, String.valueOf(LocalDateTime.now()));
//            pStmt.setString(6, String.valueOf(LocalDateTime.now()));
            pStmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * ユーザ詳細情報
     * @return
     */
    public User userReference(String id) {
        Connection conn = null;

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE id = ?";

            // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            ResultSet rs = pStmt.executeQuery();

            // 結果表に格納されたレコードの内容をUserインスタンスに設定
            while(!rs.next()) {
            	return null;
            }
            int id1 = rs.getInt("id");
            String loginId = rs.getString("login_id");
            String name = rs.getString("name");
            Date birthDate = rs.getDate("birth_date");
            String password = rs.getString("password");
            String createDate = rs.getString("create_date");
            String updateDate = rs.getString("update_date");

            return new User(id1, loginId, name, birthDate, password, createDate, updateDate);

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        // データベース切断
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	                return null;
	            }
	        }
	    }
    }


    /**
     * ログインIDチェック
     * @return
     */
    public boolean loginIdCheck(String loginId) {
        Connection conn = null;

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT login_id FROM user;";

             // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String existLoginId = rs.getString("login_id");
                if(existLoginId.equals(loginId)) {
                		return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * ユーザ情報更新（全て）
     * @throws NoSuchAlgorithmException
     */
    public void userUpdate(String password, String name, String birthDate, String loginId) throws NoSuchAlgorithmException {
        Connection conn = null;

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

	    		//パスワード暗号化
	    		String source = password;
	    		Charset charset = StandardCharsets.UTF_8;
	    		String algorithm = "MD5";
	    		byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
	    		String result = DatatypeConverter.printHexBinary(bytes);

            // SELECT文を準備
            String sql = "UPDATE user SET password = ?, name = ?, birth_date = ?, update_date = now() WHERE login_id = ?";

            // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, result);
            pStmt.setString(2, name);
            pStmt.setString(3, birthDate);
            pStmt.setString(4, loginId);
            pStmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // データベース切断
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
    }


    /**
     * ユーザ情報更新（パスワード以外）
     */
    public void userUpdate(String name, String birthDate, String loginId) {
        Connection conn = null;

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "UPDATE user SET name = ?, birth_date = ? WHERE login_id = ?";

            // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);
            pStmt.setString(2, birthDate);
            pStmt.setString(3, loginId);
            pStmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // データベース切断
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
    }

    /**
     * ユーザ削除
     */
    public void userDelete(String id) {
        Connection conn = null;

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "DELETE FROM user WHERE id = ?";

            // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            pStmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // データベース切断
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
    }


}