import * as React from "react";

export const Square =(props: {
    value: string,
    onClick: () => void
})=>{
    return (
        <button
            className="square"
            onClick={props.onClick}
        >
            {props.value}
        </button>
    );
}
