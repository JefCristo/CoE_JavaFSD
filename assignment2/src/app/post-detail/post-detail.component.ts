import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from '../post.service';
import { Post } from '../post';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})
export class PostDetailComponent implements OnInit {
  post!: Post;

  constructor(private route: ActivatedRoute, private postService: PostService) {}

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.postService.getPost(id).subscribe(post => {
      this.post = post;
    });
  }
}