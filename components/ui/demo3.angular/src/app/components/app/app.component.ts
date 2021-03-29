import {Component} from '@angular/core';
import {Backend} from '../../services/backend';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(public backend: Backend) {}
}
