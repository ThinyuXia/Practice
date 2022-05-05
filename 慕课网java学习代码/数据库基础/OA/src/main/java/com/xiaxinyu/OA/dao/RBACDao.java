package com.xiaxinyu.OA.dao;

import com.xiaxinyu.OA.entity.Node;
import com.xiaxinyu.OA.utils.MybatisUtils;

import java.util.List;

public class RBACDao {
      public List<Node> selectNodeByUserId(Long userId){
          return (List)MybatisUtils.executeQuery(sqlSession -> sqlSession.selectList("rbacmapper.selectNodeByUserId",userId));
      }
}
