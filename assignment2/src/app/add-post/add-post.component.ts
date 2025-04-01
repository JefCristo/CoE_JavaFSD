import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PostService } from '../post.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent {
  postForm: FormGroup;

  constructor(private fb: FormBuilder, private postService: PostService, private router: Router) {
    this.postForm = this.fb.group({
      title: ['', Validators.required],
      body: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.postForm.valid) {
      const newPost = this.postForm.value;
      this.postService.addPost(newPost).subscribe(() => {
        this.router.navigate(['/']);
      });
    }
  }
}