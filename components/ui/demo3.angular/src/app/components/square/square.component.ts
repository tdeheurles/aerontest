import {Component, Input} from '@angular/core';
import {Backend} from '../../services/backend';

@Component({
  selector: 'app-square',
  templateUrl: './square.component.html',
  styleUrls: ['./square.component.css']
})
export class SquareComponent {
  @Input() position!: number;
  constructor(public backend: Backend) {}

  sendMessage(): void {
    this.backend.sendMove(this.position);
  }
}
