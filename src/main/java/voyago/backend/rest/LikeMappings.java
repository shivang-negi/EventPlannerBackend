package voyago.backend.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import voyago.backend.DTO.LikeDTO.LikeDTO;
import voyago.backend.entity.Like;
import voyago.backend.service.LikeService;
import voyago.backend.utils.ResponseWrapper;
import voyago.backend.utils.Status;

@RestController
@RequestMapping("/like")
public class LikeMappings {
	
	private LikeService likeService;
	
	public LikeMappings(LikeService likeService) {
		this.likeService = likeService;
	}
	
	@PostMapping("/likePost")
	public ResponseEntity<ResponseWrapper<Like>> likePost(@RequestBody LikeDTO likeDto) {
		Like like = likeService.likePost(likeDto.getUser(), likeDto.getPost());
		return ResponseEntity.status(200).body(new ResponseWrapper<>(Status.SUCCESS, "Liked post.", like));
	}
	
	@DeleteMapping("/unlikePost")
	public ResponseEntity<ResponseWrapper<String>> unlikePost(@RequestBody LikeDTO likeDto) {
		likeService.unlikePost(likeDto.getUser(), likeDto.getPost());
		return ResponseEntity.status(200).body(new ResponseWrapper<>(Status.SUCCESS, "Unliked post"));
	}
}
