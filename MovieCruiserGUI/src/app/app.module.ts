import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MovieModule } from '../app/modules/movie/movie.module';
import { SharedModule } from '../app/modules/shared/shared.module';
import { AuthenticationModule } from '../app/modules/authentication/authentication.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardService } from './auth-guard.service';

const appRoutes:Routes = [

  {
    path:'',
    redirectTo: '/login',
    pathMatch:'full'
  }
]


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MovieModule,
    SharedModule,
    AuthenticationModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
