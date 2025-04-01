import { Pipe, PipeTransform } from '@angular/core';
import { Post } from './post';

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {
  transform(posts: Post[], searchTerm: string): Post[] {
    if (!searchTerm) return posts;
    return posts.filter(post =>
      post.title.toLowerCase().includes(searchTerm.toLowerCase())
    );
  }
}