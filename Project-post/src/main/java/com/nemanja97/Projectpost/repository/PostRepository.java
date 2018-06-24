package com.nemanja97.Projectpost.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nemanja97.Projectpost.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	Post findByComments_Id(Integer id);
	List<Post> findByTags_Id(Integer id);
	
	List<Post> findAllByOrderByDate();
	List<Post> findAllByOrderByLike();
	List<Post> findAllByOrderByDislike();
	
	@Query(value = "SELECT DISTINCT p.post_id,p.date,p.description,p.dislikes,p.latitude,p.likes,p.longitude,p.photo" +
            ",p.title,p.user_id  FROM posts p JOIN  users u ON p.user_id=u.user_id JOIN post_tags pt ON p.post_id=pt.post_id JOIN tags t ON pt.tag_id=t.tag_id " +
            "WHERE t.name LIKE %:text% OR u.username LIKE %:text%",nativeQuery = true)
    List<Post> findAllBySearch(@Param("text") String text);
}
