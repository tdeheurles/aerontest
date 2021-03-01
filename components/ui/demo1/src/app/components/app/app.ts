import {Component} from '@angular/core';
import {Backend} from "../../services/backend";

@Component({
  selector: 'app-root',
  templateUrl: './app.html'
})
export class App {
  readonly title = 'Demo 1';

  constructor(private backend: Backend) {
    console.log(`${this.constructor.name} - constructor`);
  }

  ngOnInit(): void {
    console.log(`${this.constructor.name} - ngOnInit`);
  }

  sendMessage() {
    this.backend.sendMessage("hey");
  }
}
