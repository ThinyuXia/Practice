package com.xiaxinyu.mybatis;

import com.xiaxinyu.mybatis.dto.GoodsDTO;
import com.xiaxinyu.mybatis.entity.Goods;
import com.xiaxinyu.mybatis.entity.GoodsDetail;
import com.xiaxinyu.mybatis.entity.Student;
import com.xiaxinyu.mybatis.utils.MybatisUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTestor {
    @Test
    public void testSqlSessionFactory() throws IOException {

        //利用Reader加载classpath路径下的mybatis-config核心配置文件
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        //初始化SqlSessionFactory对象,同时解析核心配置文件
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        System.out.println("SqlSessionFactory加载成功");
        SqlSession sqlSession = null;
        //创建SqlSession对象,SqlSession是JDBC的扩展类,用于与数据库交互
        try {
            sqlSession = sqlSessionFactory.openSession();
            //测试是否成功创建连接(测试用)
            //        Connection conn = sqlSession.getConnection();
            //        System.out.println(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null){
                 //type是POOLED时，将连接回收到连接池中
                //type是UNPOOLED时，将连接关闭,调用底层connection.close()方法
                 sqlSession.close();
            }
        }
    }

    @Test
    public void testMybatisUtils(){
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtils.openSession();
            Connection conn = sqlSession.getConnection();
            System.out.println(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            MybatisUtils.closeSession(sqlSession);
        }

    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = null;
        try{
            sqlSession = MybatisUtils.openSession();
            List<Student> stus = sqlSession.selectList("student.selectAll");
            for(Student stu : stus){
                System.out.println(stu.getName());
            }
        }catch(Exception e){
            throw e;
        }finally {
            MybatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testSelectById(){
        SqlSession sqlSession = null;
        try{
            sqlSession = MybatisUtils.openSession();
            Goods goods = sqlSession.selectOne("goods.selectById",1602);
            System.out.println(goods.getTitle());
        }catch (Exception e){
            throw e;
        }finally {
            MybatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testSelectByPriceRange(){
        SqlSession sqlSession = null;
        try{
            sqlSession = MybatisUtils.openSession();
            Map<String,Integer> param = new HashMap<>();
            param.put("min",100);
            param.put("max",500);
            param.put("limit",10);
            List<Goods> goodsList = sqlSession.selectList("goods.selectByPriceRange",param);
            for(Goods goods : goodsList){
                System.out.println(goods.getTitle());
            }
        }catch (Exception e){
            throw e;
        }finally {
            MybatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testSelectGoodsMap(){
        SqlSession sqlSession = null;
        try{
            sqlSession = MybatisUtils.openSession();

            List<Map> list = sqlSession.selectList("goods.selectGoodsMap");
            for(Map map : list){
                System.out.println(map);
            }
        }catch (Exception e){
            throw e;
        }finally {
            MybatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testSelectGoodsDTO(){
        SqlSession sqlSession = null;
        try{
            sqlSession = MybatisUtils.openSession();

            List<GoodsDTO> list = sqlSession.selectList("goods.selectGoodsDTO");
            for(GoodsDTO goodsDTO : list){
                System.out.println(goodsDTO.getGoods());
            }
        }catch (Exception e){
            throw e;
        }finally {
            MybatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession = null;
        try{
            sqlSession = MybatisUtils.openSession();
            Goods goods = new Goods();
            goods.setTitle("测试商品");
            goods.setSubTitle("测试子标题");
            goods.setOriginalCost(100f);
            goods.setCurrentPrice(200f);
            goods.setDiscount(0.5f);
            goods.setIsFreeDelivery(1);
            goods.setCategoryId(43);
            //insert() 返回值代表本次操作成功插入的记录总数

            Student student = new Student();
            student.setAge(20);
            student.setGrade("大二");
            student.setMajor("计科");
            student.setName("xiaxinyu");
            student.setRegNo(20203644);
            student.setSex("男");

            int num = sqlSession.insert("student.insert",student);
            sqlSession.commit(); //提交事务数据
            System.out.println(num);
            System.out.println(student.getId());
        }catch (Exception e){
            if(sqlSession != null) sqlSession.rollback();
            throw e;
        }finally {
            MybatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession = null;
        try{
            sqlSession = MybatisUtils.openSession();

            Goods good = sqlSession.selectOne("goods.selectById",739);
            good.setTitle("更新测试商品");
            int num = sqlSession.update("goods.update",good);
            System.out.println(num);
            sqlSession.commit();
        }catch (Exception e){
            if(sqlSession != null)
                sqlSession.rollback();
            throw e;
        }finally {
            MybatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testDelete(){
        SqlSession sqlSession = null;
        try{
            sqlSession = MybatisUtils.openSession();
            int num = sqlSession.update("goods.delete",739);
            System.out.println(num);
            sqlSession.commit();
        }catch (Exception e){
            if(sqlSession != null)
                sqlSession.rollback();
            throw e;
        }finally {
            MybatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testDynamicSQL(){
        SqlSession sqlSession = null;
        try{
            sqlSession = MybatisUtils.openSession();
            Map param = new HashMap();
            param.put("categoryId",44);
            param.put("currentPrice",400);
            List<Goods> goods = sqlSession.selectList("goods.dynamicSQL",param);


            sqlSession.commit();
        }catch (Exception e){
            if(sqlSession != null)
                sqlSession.rollback();
            throw e;
        }finally {
            MybatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testLv1Cache(){
        SqlSession sqlSession = null;
        try{
            sqlSession = MybatisUtils.openSession();
            Goods goods = sqlSession.selectOne("goods.selectById",1603);
            Goods goods1 = sqlSession.selectOne("goods.selectById",1603);
            System.out.println(goods.hashCode());
            System.out.println(goods1.hashCode());
        }catch (Exception e){
            throw e;
        }finally {
            MybatisUtils.closeSession(sqlSession);
        }


        try{
            sqlSession = MybatisUtils.openSession();
            Goods goods = sqlSession.selectOne("goods.selectById",1603);
            Goods goods1 = sqlSession.selectOne("goods.selectById",1603);
            System.out.println(goods.hashCode());
            System.out.println(goods1.hashCode());
        }catch (Exception e){
            throw e;
        }finally {
            MybatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testLv2Cache(){
        SqlSession sqlSession = null;
        try{
            sqlSession = MybatisUtils.openSession();
            Goods goods = sqlSession.selectOne("goods.selectById",1603);
            System.out.println(goods.hashCode());
        }catch (Exception e){
            throw e;
        }finally {
            MybatisUtils.closeSession(sqlSession);
        }

        try{
            sqlSession = MybatisUtils.openSession();
            Goods goods = sqlSession.selectOne("goods.selectById",1603);
            System.out.println(goods.hashCode());
        }catch (Exception e){
            throw e;
        }finally {
            MybatisUtils.closeSession(sqlSession);
        }
    }
    
    @Test
    public void testOneToMany(){
        SqlSession sqlSession = null;
        try{
            sqlSession = MybatisUtils.openSession();
            List<Goods> list = sqlSession.selectList("goods.selectOneToMany");
            for(Goods goods : list ){
                System.out.println(goods.getTitle() + goods.getGoodsDetails().size());
            }
        }catch (Exception e){
            throw e;
        }finally {
            MybatisUtils.closeSession(sqlSession);
        }
    }

    @Test
    public void testManyToOne(){
        SqlSession sqlSession = null;
        try{
            sqlSession = MybatisUtils.openSession();
            List<GoodsDetail> list = sqlSession.selectList("goodsDetail.selectManyToOne");
            System.out.println(list.get(0).getGoods());
            for(GoodsDetail gd : list ){
                System.out.println(gd.getGdPicUrl() + gd.getGoods().getTitle());
            }
        }catch (Exception e){
            throw e;
        }finally {
            MybatisUtils.closeSession(sqlSession);
        }
    }
}
