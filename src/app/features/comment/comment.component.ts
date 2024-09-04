import { Component, Input } from '@angular/core';
import { Comment } from '../../models/interfaces';
import { CommonModule } from '@angular/common';
import { UserService } from '../../services/user.service';
import { CommentService } from '../../services/comment.service';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-comment',
  standalone: true,
  imports: [CommentComponent, CommonModule, FormsModule],
  templateUrl: './comment.component.html',
  styleUrl: './comment.component.css'
})
export class CommentComponent {
  @Input() comment: any;


  userData:any;
  note:string = '';
  commentData={
    userId:0,
    text:"",
    parentCommentId:0,
    reviewId:0,
    replies:[]
  }

  text:string = '';

  constructor(
    private userService:UserService,
    private commentService:CommentService
  ){

  }

  ngOnInit(){
   this.userService.getUserInfoByUserId(this.comment.userId).subscribe(data=>{
    this.userData = data;
   },error=>{
    this.note = error.error.message;
   });
   this.userService.getUserInfo().subscribe(data=>{
    this.commentData.userId = data.id;
    this.commentData.parentCommentId = this.comment.id;
    this.commentData.reviewId = this.comment.reviewId;
   },error=>{
    this.note = error.error.message;
   })
  }

  onAddReply(){
    this.commentData.text = this.text;
    this.commentService.addReply(this.commentData).subscribe(
      (response) => {
        window.location.reload();        
      },
      (error) => {
        console.log(error);
        this.note = error.error.message;
      }
    );
  }
}
