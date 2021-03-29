import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './components/app/app.component';
import {Backend} from './services/backend';
import {SquareComponent} from './components/square/square.component';

@NgModule({
  declarations: [
    AppComponent,
    SquareComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [Backend],
  bootstrap: [AppComponent]
})
export class AppModule {
}
