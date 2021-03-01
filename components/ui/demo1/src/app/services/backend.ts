import {Injectable} from "@angular/core";
import {webSocket} from "rxjs/webSocket";
import {NextObserver, Subject} from "rxjs";
import {delay, retryWhen, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class Backend {
  private openSubject: NextObserver<Event> = {
    next: _ => console.log("Connection to backend opened")
  }

  private closeSubject: NextObserver<CloseEvent> = {
    next: _ => console.log("Connection to backend closed")
  };

  readonly websocket = webSocket({
    "url": "ws:localhost:8081",
    closeObserver: this.closeSubject,
    openObserver: this.openSubject
  });

  constructor() {
    console.log(`${this.constructor.name} - constructor`);
    this.websocket
      .pipe(
        retryWhen(
          (errors) => errors.pipe(
            tap(_ => console.log("retrying in 500ms ...")),
            delay(500)
          ))
      )
      .subscribe(
        msg => console.log(msg),
        err => console.log(err),
        () => console.log("complete")
      );
  }

  sendMessage(message: string) {
    this.websocket.next({"message": message, "value": 2, "isOk": false});
  }
}
