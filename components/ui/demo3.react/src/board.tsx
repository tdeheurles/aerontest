import * as React from 'react';
import {Square} from './square';
import {webSocket} from 'rxjs/webSocket';
import {delay, retryWhen, tap} from 'rxjs/operators';
import {com} from './generated/message';
import {Dispatch, SetStateAction} from 'react';

export const Board =()=>{
    const [backend] = React.useState(
        webSocket({
            url: 'ws:localhost:8081',
            closeObserver: {next: _ => console.log('Connection to backend closed')},
            openObserver: {next: _ => console.log('Connection to backend opened')},
            binaryType: 'arraybuffer',
            serializer: v => v as ArrayBuffer,
            deserializer: v => v.data
    }));
    const [xIsNext, setXIsNext] = React.useState(true);
    const [state, setStatus] = React.useState('Next player: ' + (xIsNext ? 'X' : 'O'));
    const [squares, setSquares]: [string[], Dispatch<SetStateAction<string[]>>] = React.useState(Array(9).fill(""));

    const receiveMessage = React.useCallback(
        (wrapper: any): void => {
            try {
                console.log('========== receiving message ==========');
                const wrapperAsUint8Array = new Uint8Array(wrapper as ArrayBuffer);
                const wrapperInstance = com.tdeheurles.aerontest.protobuf.Demo3Wrapper.decode(wrapperAsUint8Array);
                if (wrapperInstance.fullState) {
                    console.log('---- fullStateMessage')
                    const squares = wrapperInstance.fullState.squares;
                    if (squares) {
                        setSquares(squares);
                        console.log('squares set: ' + squares);
                    }

                    const winner = wrapperInstance.fullState.winner;
                    console.log('winner: ' + winner)
                    const xIsNext = wrapperInstance.fullState.xIsNext;
                    if (xIsNext) {
                        setXIsNext(xIsNext)
                        console.log('xIsNext: ' + xIsNext)
                    }

                    if (winner) {
                        setStatus('Winner: ' + winner);
                    } else {
                        setStatus('Next player: ' + (xIsNext ? 'X' : 'O'));
                    }
                }
                else if (wrapperInstance.fullStateRequest) {
                    console.log('---- fullStateRequest');
                    console.log("nothing to do");
                }
                else if (wrapperInstance.move) {
                    console.log('---- move');
                    console.log("nothing to do");
                }
                else if (wrapperInstance.reset) {
                    console.log('---- reset');
                    console.log("nothing to do");
                }
                else {
                    console.log("unknown message type");
                }
            }
            catch (e) {
                console.error(e);
            }
        }, [setSquares, setXIsNext]
    )

    const sendFullStateRequest = React.useCallback(
        (): void => {
            try {
                console.log('========== sending fullStateRequest message ==========');
                const fullStateRequest = com.tdeheurles.aerontest.protobuf.Demo3Move.create();
                const wrapper = com.tdeheurles.aerontest.protobuf.Demo3Wrapper.create({fullStateRequest});
                const encoded = com.tdeheurles.aerontest.protobuf.Demo3Wrapper.encode(wrapper).finish();
                backend.next(encoded);
            } catch (e) {
                console.error(e);
            }
        }, [backend]
    )

    const sendMove = React.useCallback(
        (position: number): void => {
            try {
                console.log('========== sending move message ==========');
                const move = com.tdeheurles.aerontest.protobuf.Demo3Move.create({position});
                const wrapper = com.tdeheurles.aerontest.protobuf.Demo3Wrapper.create({move});
                const encoded = com.tdeheurles.aerontest.protobuf.Demo3Wrapper.encode(wrapper).finish();
                backend.next(encoded);
            } catch (e) {
                console.error(e);
            }
        }, [backend]
    );

    const sendReset = React.useCallback(
        (): void => {
            try {
                console.log('========== sending reset message ==========');
                const reset = com.tdeheurles.aerontest.protobuf.Demo3Reset.create();
                const wrapper = com.tdeheurles.aerontest.protobuf.Demo3Wrapper.create({reset});
                const encoded = com.tdeheurles.aerontest.protobuf.Demo3Wrapper.encode(wrapper).finish();
                backend.next(encoded);
            } catch (e) {
                console.error(e);
            }
        }, [backend]
    );

    // websocket handshake
    React.useEffect(() => {
        backend.pipe(
                retryWhen(
                    (errors) => errors.pipe(
                        tap(_ => console.log('retrying in 500ms ...')),
                        delay(500)
                    ))
            )
            .subscribe(
                msg => receiveMessage(msg),
                err => console.log(err),
                () => console.log('complete')
            );

        sendFullStateRequest();
        }, [backend, receiveMessage, sendFullStateRequest]);

    const renderSquare = React.useCallback(
        (i)=> <Square
            value={squares[i]}
            onClick={() => sendMove(i)}/>,
        [sendMove, squares]);

    return <>
        <div className="status">{state}</div>
        <div className="board-row">
            {renderSquare(0)}
            {renderSquare(1)}
            {renderSquare(2)}
        </div>
        <div className="board-row">
            {renderSquare(3)}
            {renderSquare(4)}
            {renderSquare(5)}
        </div>
        <div className="board-row">
            {renderSquare(6)}
            {renderSquare(7)}
            {renderSquare(8)}
        </div>
        <button
            className="reset"
            onClick={()=>sendReset()}>
            reset
        </button>
    </>
}
