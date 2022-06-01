package com.xiaxinyu.ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class SongsListServlet
 */
@WebServlet("/song")
public class SongsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SongsListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Song> songs = new ArrayList<>();
		songs.add(new Song("流行歌曲","歌曲1"));
		songs.add(new Song("流行歌曲","歌曲2"));
		songs.add(new Song("流行歌曲","歌曲3"));
		songs.add(new Song("经典歌曲","歌曲4"));
		songs.add(new Song("经典歌曲","歌曲5"));
		songs.add(new Song("经典歌曲","歌曲6"));
		songs.add(new Song("摇滚歌曲","歌曲7"));
		songs.add(new Song("摇滚歌曲","歌曲8"));
		songs.add(new Song("摇滚歌曲","歌曲9"));
		response.setContentType("text/html;charset=utf-8");
		String json = JSON.toJSONString(songs);
		response.getWriter().println(json);
	}



}
