import { Component, OnInit } from '@angular/core';
import { PostService } from '../post.service';
import { Post } from '../post';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  posts: Post[] = [];
  searchTerm: string = '';

  constructor(private postService: PostService, private router: Router) {}

  ngOnInit() {
   
    this.postService.getPosts().subscribe(posts => {
      this.posts = posts;
    });
  }

  logout() {
    localStorage.removeItem('loggedIn');
    this.router.navigate(['/login']);
  }

  onFavorite(post: Post) {
    console.log('Liked post:', post.title);
  }
}