package com.tdeheurles.aeronclusterbasicsample;

import io.aeron.cluster.RecordingLog;

public class ReplicatedStateMachine
{
    final RecordingLog.Snapshot snapshot = new RecordingLog.Snapshot();
    final CurrentValueEvent currentValueEvent = new CurrentValueEvent();
    private int currentValue;

    public void add(AddCommand addCommand,
                    ExpandableDirectByteBuffer returnBuffer)
    {
        currentValue += addCommand.readValue();
        prepareCurrentValueEvent(returnBuffer);
    }

    public void multiply(MultiplyCommand multiplyCommand,
                         ExpandableDirectByteBuffer returnBuffer)
    {
        currentValue *= multiplyCommand.readValue();
        prepareCurrentValueEvent(returnBuffer);
    }

    public void setCurrentValue(SetCommand setCommand,
                                ExpandableDirectByteBuffer returnBuffer)
    {
        currentValue = setCommand.readValue();
        prepareCurrentValueEvent(returnBuffer);
    }

    public void takeSnapshot(ExpandableDirectByteBuffer buffer)
    {
        snapshot.setUnderlyingBuffer(buffer, 0);
        snapshot.writeHeader();
        snapshot.writeValue(currentValue);
    }

    public void loadFromSnapshot(Snapshot snapshot)
    {
        currentValue = snapshot.readValue();
    }

    private void prepareCurrentValueEvent(ExpandableDirectByteBuffer
                                                  returnBuffer)
    {
        currentValueEvent.setUnderlyingBuffer(returnBuffer, 0);
        currentValueEvent.writeHeader();
        currentValueEvent.writeValue(currentValue);
    }
}