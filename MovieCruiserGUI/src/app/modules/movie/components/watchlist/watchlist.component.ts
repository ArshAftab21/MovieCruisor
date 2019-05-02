import { Component, OnInit } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService} from '../../movie-service.service';

@Component({
  selector: 'app-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {

  movies:Array<Movie>
  usewatchListApi=true;

  constructor(private movieService:MovieService) { 
    this.movies=[];
  }

  ngOnInit() {
    this.movieService.getWatchListedMovies().subscribe((movies) => {
       this.movies.push(...movies);    
    })
  }

}
