import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {App} from './components/app/app';
import {Backend} from "./services/backend";

@NgModule({
  declarations: [
    App
  ],
  imports: [
    BrowserModule
  ],
  providers: [Backend],
  bootstrap: [App]
})
export class AppModule {
}
