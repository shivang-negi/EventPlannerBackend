package voyago.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import voyago.backend.DTO.CommentDTO.FetchCommentsDTO;
import voyago.backend.DTO.CommentDTO.SaveCommentDTO;
import voyago.backend.entity.Comment;
import voyago.backend.service.CommentService;
import voyago.backend.utils.ResponseWrapper;
import voyago.backend.utils.Status;

@RestController
@RequestMapping("/comment")
public class CommentMappings {
	
	CommentService commentService;
	
	@Autowired
	public CommentMappings(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping("/saveComment")
	public ResponseEntity<ResponseWrapper<Comment>> saveComment(@RequestBody SaveCommentDTO commentDTO) {
		Comment comment = commentService.saveComment(commentDTO);
		return ResponseEntity.ok(new ResponseWrapper<>(Status.SUCCESS,"Comment saved.",comment));
	}
	
	@GetMapping("/getComments")
	public ResponseEntity<ResponseWrapper<FetchCommentsDTO>> fetchComments(@RequestParam String postId) {
		FetchCommentsDTO comments = commentService.fetchComments(postId);
		return ResponseEntity.ok(new ResponseWrapper<>(Status.SUCCESS,"Comments fetched",comments));
	}
}
