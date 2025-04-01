import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Post } from '../post';

@Component({
  selector: 'app-post-item',
  templateUrl: './post-item.component.html',
  styleUrls: ['./post-item.component.css']
})
export class PostItemComponent {
  @Input() post!: Post;
  @Output() favorite = new EventEmitter<Post>();

  onFavorite() {
    this.favorite.emit(this.post);
  }
}