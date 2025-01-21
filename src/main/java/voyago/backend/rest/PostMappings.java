package voyago.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import voyago.backend.DTO.PostDTO.PostByIdDTO;
import voyago.backend.DTO.PostDTO.PostListDTO;
import voyago.backend.DTO.PostDTO.SavePostDTO;
import voyago.backend.entity.Post;
import voyago.backend.service.PostService;
import voyago.backend.utils.ResponseWrapper;
import voyago.backend.utils.Status;

@RestController
@RequestMapping("/post")
public class PostMappings {
	PostService postService;
	
	@Autowired
	public PostMappings(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping("/postById")
	public ResponseEntity<ResponseWrapper<Post>> postById(@RequestBody PostByIdDTO user) {
		Post post = postService.findById(user.getId());
		return ResponseEntity.status(200).body(new ResponseWrapper<Post>(Status.SUCCESS,"Fetched post successfully",post));
	}
	
	@PostMapping("/savePost")
	public ResponseEntity<ResponseWrapper<Post>> savePost(@RequestBody SavePostDTO postDTO) {
		Post post = postService.savePost(postDTO);
		return ResponseEntity.status(200).body(new ResponseWrapper<Post>(Status.SUCCESS,"Saved post successfully",post));
	}
	
	@GetMapping(value = {"/getPostList/{pageNo}", "/getPostList/{pageNo}/{userId}"})
	public ResponseEntity<ResponseWrapper<PostListDTO>> getPostList(@PathVariable(required = false) Integer pageNo, @PathVariable String userId) {
		int page = (pageNo == null)?1:pageNo;
		PostListDTO posts = postService.getPostList(page, userId);
		return ResponseEntity.status(200).body(new ResponseWrapper<>(Status.SUCCESS,"List retrieved",posts));
	}
}
