// https://golb.hplar.ch/2020/04/rxjs-websocket.html

import {Injectable} from '@angular/core';
import {webSocket} from 'rxjs/webSocket';
import {NextObserver} from 'rxjs';
import {delay, retryWhen, tap} from 'rxjs/operators';
import {com} from '../../../../generated/message';

@Injectable({
  providedIn: 'root'
})
export class Backend {
  private openSubject: NextObserver<Event> = {
    next: _ => console.log('Connection to backend opened')
  };

  private closeSubject: NextObserver<CloseEvent> = {
    next: _ => console.log('Connection to backend closed')
  };

  readonly websocket = webSocket({
    url: 'ws:localhost:8081',
    closeObserver: this.closeSubject,
    openObserver: this.openSubject,

    // ProtoBuf
    binaryType: 'arraybuffer',
    serializer: v => v as ArrayBuffer,
    deserializer: v => v.data
  });

  constructor() {
    console.log(`${this.constructor.name} - constructor`);
    this.websocket
      .pipe(
        retryWhen(
          (errors) => errors.pipe(
            tap(_ => console.log('retrying in 500ms ...')),
            delay(500)
          ))
      )
      .subscribe(
        msg => this.receiveMessage(msg),
        err => console.log(err),
        () => console.log('complete')
      );
  }

  sendMessage(content: string): void {
    try {
      console.log('========== sending message ==========');
      console.log('--- Create message');
      const messageObject = com.tdeheurles.aerontest.protobuf.Demo2Message.create({content});
      console.log(messageObject);

      console.log('--- Serialize message');
      const encodedMessage = com.tdeheurles.aerontest.protobuf
        .Demo2Message.encode(messageObject).finish();
      console.log(encodedMessage);

      const offset = encodedMessage.byteOffset;
      const length = encodedMessage.byteLength;
      console.log('--- Send message with offset:(' + offset + ') - length(' + length + ')');
      this.websocket.next(encodedMessage);
      console.log('========== message sent ==========');
    }
    catch (e) {
      console.error(e);
    }
  }

  receiveMessage(message: any): void {
    try {
      console.log('========== receiving message ==========');
      console.log('--- message (ArrayBuffer):');
      console.log(message);

      console.log('--- message (Uint8Array)');
      const messageAsUint8Array = new Uint8Array(message as ArrayBuffer);
      console.log(messageAsUint8Array);

      console.log('--- decoded.content:');
      const messageInstance = com.tdeheurles.aerontest.protobuf.Demo2Message.decode(messageAsUint8Array);
      console.log(messageInstance.toJSON());

      console.log('========== message received ==========');
    }
    catch (e) {
      console.error(e);
    }
  }
}
