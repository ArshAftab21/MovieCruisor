import { Component, OnInit, Input } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService} from '../../movie-service.service';
import {MatSnackBar} from '@angular/material/snack-bar'
import { Output,EventEmitter } from '@angular/core';
import {MatDialogModule} from '@angular/material/dialog';
import { MatDialog } from '@angular/material/dialog';
import { MovieDialogComponent } from '../movie-dialog/movie-dialog.component';




@Component({
  selector: 'app-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {

  @Input() 
  movie:Movie;

  @Input()
  usewatchListApi:boolean;

  @Output()
  addMovie = new EventEmitter();

  @Output()
  deleteMovie = new EventEmitter();

  constructor(private dialog: MatDialog) { 
    
  }

  ngOnInit() {
  }

  addToWatchList()
  {
    this.addMovie.emit(this.movie);

    // this.movieService.saveWatchListMovies(this.movie).subscribe((movie) => {
    //   let message='${this.movie.title} added to watchList';
    //   this.snackbar.open(message,'',{
    //        duration:1000
    //      });
      
    // })
  }

  deleteFromWatchList(){
    this.deleteMovie.emit(this.movie);
  }

  updateFromWatchList(actionType)
{
  let dialogRef = this.dialog.open(MovieDialogComponent, {
    width: '400px', data: {obj: this.movie, actionType: actionType}
  });
  dialogRef.afterClosed().subscribe(result => {
  });

}


}
