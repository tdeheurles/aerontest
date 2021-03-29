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
  public status = '';
  public squares = ['', '', '', '', '', '', '', '', ''];
  public xIsNext = true;

  private openSubject: NextObserver<Event> = {
    next: _ => {
      console.log('Connection to backend opened');
      this.sendFullStateRequest();
    }
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

  private receiveMessage(wrapper: any): void {
    try {
      console.log('========== receiving message ==========');
      const wrapperAsUint8Array = new Uint8Array(wrapper as ArrayBuffer);
      const wrapperInstance = com.tdeheurles.aerontest.protobuf.Demo3Wrapper.decode(wrapperAsUint8Array);
      if (wrapperInstance.fullState) {
        console.log('---- fullStateMessage');
        const newSquares = wrapperInstance.fullState.squares;
        if (newSquares) {
          this.squares = newSquares;
          console.log('squares set: ' + this.squares);
        }

        const newWinner = wrapperInstance.fullState.winner;
        console.log('winner: ' + newWinner);
        const newXIsNext = wrapperInstance.fullState.xIsNext;
        if (newXIsNext) {
          this.xIsNext = newXIsNext;
          console.log('xIsNext: ' + this.xIsNext);
        }

        if (newWinner) {
          this.status = 'Winner: ' + newWinner;
        } else {
          this.status = 'Next player: ' + (newXIsNext ? 'X' : 'O');
        }
      }
      else if (wrapperInstance.fullStateRequest) {
        console.log('---- fullStateRequest');
        console.log('nothing to do');
      }
      else if (wrapperInstance.move) {
        console.log('---- move');
        console.log('nothing to do');
      }
      else if (wrapperInstance.reset) {
        console.log('---- reset');
        console.log('nothing to do');
      }
      else {
        console.log('unknown message type');
      }
    }
    catch (e) {
      console.error(e);
    }
  }

  private sendFullStateRequest(): void {
    try {
      console.log('========== sending fullStateRequest message ==========');
      const fullStateRequest = com.tdeheurles.aerontest.protobuf.Demo3Move.create();
      const wrapper = com.tdeheurles.aerontest.protobuf.Demo3Wrapper.create({fullStateRequest});
      const encoded = com.tdeheurles.aerontest.protobuf.Demo3Wrapper.encode(wrapper).finish();
      this.websocket.next(encoded);
    } catch (e) {
      console.error(e);
    }
  }

  public sendMove(position: number): void {
    try {
      console.log('========== sending move message ==========');
      const move = com.tdeheurles.aerontest.protobuf.Demo3Move.create({position});
      const wrapper = com.tdeheurles.aerontest.protobuf.Demo3Wrapper.create({move});
      const encoded = com.tdeheurles.aerontest.protobuf.Demo3Wrapper.encode(wrapper).finish();
      this.websocket.next(encoded);
    } catch (e) {
      console.error(e);
    }
  }

  public sendReset(): void {
    try {
      console.log('========== sending reset message ==========');
      const reset = com.tdeheurles.aerontest.protobuf.Demo3Reset.create();
      const wrapper = com.tdeheurles.aerontest.protobuf.Demo3Wrapper.create({reset});
      const encoded = com.tdeheurles.aerontest.protobuf.Demo3Wrapper.encode(wrapper).finish();
      this.websocket.next(encoded);
    } catch (e) {
      console.error(e);
    }
  }
}
