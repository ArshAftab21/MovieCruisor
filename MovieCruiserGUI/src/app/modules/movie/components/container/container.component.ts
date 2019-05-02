import { Component, OnInit, Input } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService} from '../../movie-service.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {

  @Input()
   movies:Array<Movie>;

   @Input()
   usewatchListApi:boolean;


  constructor(private movieService:MovieService,private router:Router,private matSnackBar:MatSnackBar) { 
     
  }

  ngOnInit() {
  }

  addMovieToWatchList(movie)
  {
    let message = `${movie.title} add to watch list`;
    this.movieService.addMoviesToWatchList(movie).subscribe((movie)=>{
   this.matSnackBar.open("Movie Added To WatchLis",'',{
      duration:1000
    });
    })
 
  }

  deleteFromWatchList(movie){
    console.log(movie);
    let message = `${movie.title} deleted from your watchlist`;
    for( var i=0;i<this.movies.length;i++){
      if(this.movies[i].title===movie.title){
        this.movies.splice(i,1);
      }
    }
    this.movieService.deleteFromMyWatchList(movie).subscribe((movie)=>{
      this.matSnackBar.open(message,'',{
        duration:1000
      });
    });

  }

}
