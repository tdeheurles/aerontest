// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/resources/DemoClass.proto

package com.tdeheurles.aerontest.reallogicsbe;

public final class ProtoDemoClass {
  private ProtoDemoClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  /**
   * Protobuf enum {@code tutorial.SampleEnum}
   */
  public enum SampleEnum
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>VALUE_1 = 0;</code>
     */
    VALUE_1(0),
    /**
     * <code>VALUE_2 = 1;</code>
     */
    VALUE_2(1),
    /**
     * <code>VALUE_3 = 2;</code>
     */
    VALUE_3(2),
    ;

    /**
     * <code>VALUE_1 = 0;</code>
     */
    public static final int VALUE_1_VALUE = 0;
    /**
     * <code>VALUE_2 = 1;</code>
     */
    public static final int VALUE_2_VALUE = 1;
    /**
     * <code>VALUE_3 = 2;</code>
     */
    public static final int VALUE_3_VALUE = 2;


    public final int getNumber() {
      return value;
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static SampleEnum valueOf(int value) {
      return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static SampleEnum forNumber(int value) {
      switch (value) {
        case 0: return VALUE_1;
        case 1: return VALUE_2;
        case 2: return VALUE_3;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<SampleEnum>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        SampleEnum> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<SampleEnum>() {
            public SampleEnum findValueByNumber(int number) {
              return SampleEnum.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.getDescriptor().getEnumTypes().get(0);
    }

    private static final SampleEnum[] VALUES = values();

    public static SampleEnum valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private SampleEnum(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:tutorial.SampleEnum)
  }

  public interface DemoClassOrBuilder extends
      // @@protoc_insertion_point(interface_extends:tutorial.DemoClass)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 sequence = 1;</code>
     * @return Whether the sequence field is set.
     */
    boolean hasSequence();
    /**
     * <code>optional int32 sequence = 1;</code>
     * @return The sequence.
     */
    int getSequence();

    /**
     * <code>optional .tutorial.SampleEnum sampleEnum = 2;</code>
     * @return Whether the sampleEnum field is set.
     */
    boolean hasSampleEnum();
    /**
     * <code>optional .tutorial.SampleEnum sampleEnum = 2;</code>
     * @return The sampleEnum.
     */
    com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.SampleEnum getSampleEnum();

    /**
     * <code>optional string message = 3;</code>
     * @return Whether the message field is set.
     */
    boolean hasMessage();
    /**
     * <code>optional string message = 3;</code>
     * @return The message.
     */
    java.lang.String getMessage();
    /**
     * <code>optional string message = 3;</code>
     * @return The bytes for message.
     */
    com.google.protobuf.ByteString
        getMessageBytes();
  }
  /**
   * Protobuf type {@code tutorial.DemoClass}
   */
  public static final class DemoClass extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:tutorial.DemoClass)
      DemoClassOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use DemoClass.newBuilder() to construct.
    private DemoClass(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private DemoClass() {
      sampleEnum_ = 0;
      message_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new DemoClass();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private DemoClass(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              bitField0_ |= 0x00000001;
              sequence_ = input.readInt32();
              break;
            }
            case 16: {
              int rawValue = input.readEnum();
                @SuppressWarnings("deprecation")
              com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.SampleEnum value = com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.SampleEnum.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(2, rawValue);
              } else {
                bitField0_ |= 0x00000002;
                sampleEnum_ = rawValue;
              }
              break;
            }
            case 26: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000004;
              message_ = bs;
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.internal_static_tutorial_DemoClass_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.internal_static_tutorial_DemoClass_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass.class, com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass.Builder.class);
    }

    private int bitField0_;
    public static final int SEQUENCE_FIELD_NUMBER = 1;
    private int sequence_;
    /**
     * <code>optional int32 sequence = 1;</code>
     * @return Whether the sequence field is set.
     */
    @java.lang.Override
    public boolean hasSequence() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>optional int32 sequence = 1;</code>
     * @return The sequence.
     */
    @java.lang.Override
    public int getSequence() {
      return sequence_;
    }

    public static final int SAMPLEENUM_FIELD_NUMBER = 2;
    private int sampleEnum_;
    /**
     * <code>optional .tutorial.SampleEnum sampleEnum = 2;</code>
     * @return Whether the sampleEnum field is set.
     */
    @java.lang.Override public boolean hasSampleEnum() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>optional .tutorial.SampleEnum sampleEnum = 2;</code>
     * @return The sampleEnum.
     */
    @java.lang.Override public com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.SampleEnum getSampleEnum() {
      @SuppressWarnings("deprecation")
      com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.SampleEnum result = com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.SampleEnum.valueOf(sampleEnum_);
      return result == null ? com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.SampleEnum.VALUE_1 : result;
    }

    public static final int MESSAGE_FIELD_NUMBER = 3;
    private volatile java.lang.Object message_;
    /**
     * <code>optional string message = 3;</code>
     * @return Whether the message field is set.
     */
    @java.lang.Override
    public boolean hasMessage() {
      return ((bitField0_ & 0x00000004) != 0);
    }
    /**
     * <code>optional string message = 3;</code>
     * @return The message.
     */
    @java.lang.Override
    public java.lang.String getMessage() {
      java.lang.Object ref = message_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          message_ = s;
        }
        return s;
      }
    }
    /**
     * <code>optional string message = 3;</code>
     * @return The bytes for message.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getMessageBytes() {
      java.lang.Object ref = message_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        message_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) != 0)) {
        output.writeInt32(1, sequence_);
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        output.writeEnum(2, sampleEnum_);
      }
      if (((bitField0_ & 0x00000004) != 0)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, message_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, sequence_);
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(2, sampleEnum_);
      }
      if (((bitField0_ & 0x00000004) != 0)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, message_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass)) {
        return super.equals(obj);
      }
      com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass other = (com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass) obj;

      if (hasSequence() != other.hasSequence()) return false;
      if (hasSequence()) {
        if (getSequence()
            != other.getSequence()) return false;
      }
      if (hasSampleEnum() != other.hasSampleEnum()) return false;
      if (hasSampleEnum()) {
        if (sampleEnum_ != other.sampleEnum_) return false;
      }
      if (hasMessage() != other.hasMessage()) return false;
      if (hasMessage()) {
        if (!getMessage()
            .equals(other.getMessage())) return false;
      }
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasSequence()) {
        hash = (37 * hash) + SEQUENCE_FIELD_NUMBER;
        hash = (53 * hash) + getSequence();
      }
      if (hasSampleEnum()) {
        hash = (37 * hash) + SAMPLEENUM_FIELD_NUMBER;
        hash = (53 * hash) + sampleEnum_;
      }
      if (hasMessage()) {
        hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
        hash = (53 * hash) + getMessage().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code tutorial.DemoClass}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:tutorial.DemoClass)
        com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClassOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.internal_static_tutorial_DemoClass_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.internal_static_tutorial_DemoClass_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass.class, com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass.Builder.class);
      }

      // Construct using com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        sequence_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        sampleEnum_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        message_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.internal_static_tutorial_DemoClass_descriptor;
      }

      @java.lang.Override
      public com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass getDefaultInstanceForType() {
        return com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass.getDefaultInstance();
      }

      @java.lang.Override
      public com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass build() {
        com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass buildPartial() {
        com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass result = new com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.sequence_ = sequence_;
          to_bitField0_ |= 0x00000001;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          to_bitField0_ |= 0x00000002;
        }
        result.sampleEnum_ = sampleEnum_;
        if (((from_bitField0_ & 0x00000004) != 0)) {
          to_bitField0_ |= 0x00000004;
        }
        result.message_ = message_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass) {
          return mergeFrom((com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass other) {
        if (other == com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass.getDefaultInstance()) return this;
        if (other.hasSequence()) {
          setSequence(other.getSequence());
        }
        if (other.hasSampleEnum()) {
          setSampleEnum(other.getSampleEnum());
        }
        if (other.hasMessage()) {
          bitField0_ |= 0x00000004;
          message_ = other.message_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int sequence_ ;
      /**
       * <code>optional int32 sequence = 1;</code>
       * @return Whether the sequence field is set.
       */
      @java.lang.Override
      public boolean hasSequence() {
        return ((bitField0_ & 0x00000001) != 0);
      }
      /**
       * <code>optional int32 sequence = 1;</code>
       * @return The sequence.
       */
      @java.lang.Override
      public int getSequence() {
        return sequence_;
      }
      /**
       * <code>optional int32 sequence = 1;</code>
       * @param value The sequence to set.
       * @return This builder for chaining.
       */
      public Builder setSequence(int value) {
        bitField0_ |= 0x00000001;
        sequence_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 sequence = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearSequence() {
        bitField0_ = (bitField0_ & ~0x00000001);
        sequence_ = 0;
        onChanged();
        return this;
      }

      private int sampleEnum_ = 0;
      /**
       * <code>optional .tutorial.SampleEnum sampleEnum = 2;</code>
       * @return Whether the sampleEnum field is set.
       */
      @java.lang.Override public boolean hasSampleEnum() {
        return ((bitField0_ & 0x00000002) != 0);
      }
      /**
       * <code>optional .tutorial.SampleEnum sampleEnum = 2;</code>
       * @return The sampleEnum.
       */
      @java.lang.Override
      public com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.SampleEnum getSampleEnum() {
        @SuppressWarnings("deprecation")
        com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.SampleEnum result = com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.SampleEnum.valueOf(sampleEnum_);
        return result == null ? com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.SampleEnum.VALUE_1 : result;
      }
      /**
       * <code>optional .tutorial.SampleEnum sampleEnum = 2;</code>
       * @param value The sampleEnum to set.
       * @return This builder for chaining.
       */
      public Builder setSampleEnum(com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.SampleEnum value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000002;
        sampleEnum_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <code>optional .tutorial.SampleEnum sampleEnum = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearSampleEnum() {
        bitField0_ = (bitField0_ & ~0x00000002);
        sampleEnum_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object message_ = "";
      /**
       * <code>optional string message = 3;</code>
       * @return Whether the message field is set.
       */
      public boolean hasMessage() {
        return ((bitField0_ & 0x00000004) != 0);
      }
      /**
       * <code>optional string message = 3;</code>
       * @return The message.
       */
      public java.lang.String getMessage() {
        java.lang.Object ref = message_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            message_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>optional string message = 3;</code>
       * @return The bytes for message.
       */
      public com.google.protobuf.ByteString
          getMessageBytes() {
        java.lang.Object ref = message_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          message_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>optional string message = 3;</code>
       * @param value The message to set.
       * @return This builder for chaining.
       */
      public Builder setMessage(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        message_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional string message = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearMessage() {
        bitField0_ = (bitField0_ & ~0x00000004);
        message_ = getDefaultInstance().getMessage();
        onChanged();
        return this;
      }
      /**
       * <code>optional string message = 3;</code>
       * @param value The bytes for message to set.
       * @return This builder for chaining.
       */
      public Builder setMessageBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        message_ = value;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:tutorial.DemoClass)
    }

    // @@protoc_insertion_point(class_scope:tutorial.DemoClass)
    private static final com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass();
    }

    public static com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<DemoClass>
        PARSER = new com.google.protobuf.AbstractParser<DemoClass>() {
      @java.lang.Override
      public DemoClass parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new DemoClass(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<DemoClass> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<DemoClass> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.tdeheurles.aerontest.reallogicsbe.ProtoDemoClass.DemoClass getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tutorial_DemoClass_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tutorial_DemoClass_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\"src/main/resources/DemoClass.proto\022\010tu" +
      "torial\"X\n\tDemoClass\022\020\n\010sequence\030\001 \001(\005\022(\n" +
      "\nsampleEnum\030\002 \001(\0162\024.tutorial.SampleEnum\022" +
      "\017\n\007message\030\003 \001(\t*3\n\nSampleEnum\022\013\n\007VALUE_" +
      "1\020\000\022\013\n\007VALUE_2\020\001\022\013\n\007VALUE_3\020\002B7\n%com.tde" +
      "heurles.aerontest.reallogicsbeB\016ProtoDem" +
      "oClass"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_tutorial_DemoClass_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_tutorial_DemoClass_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tutorial_DemoClass_descriptor,
        new java.lang.String[] { "Sequence", "SampleEnum", "Message", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
