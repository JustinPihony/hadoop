/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.yarn.server.api.protocolrecords.impl.pb;

import org.apache.hadoop.classification.InterfaceAudience.Private;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.NodeId;
import org.apache.hadoop.yarn.api.records.impl.pb.ApplicationIdPBImpl;
import org.apache.hadoop.yarn.api.records.impl.pb.NodeIdPBImpl;
import org.apache.hadoop.yarn.proto.YarnProtos.ApplicationIdProto;
import org.apache.hadoop.yarn.proto.YarnProtos.NodeIdProto;
import org.apache.hadoop.yarn.proto.YarnServerCommonProtos.LogAggregationStatusProto;
import org.apache.hadoop.yarn.proto.YarnServerCommonServiceProtos.LogAggregationReportProto;
import org.apache.hadoop.yarn.proto.YarnServerCommonServiceProtos.LogAggregationReportProtoOrBuilder;
import org.apache.hadoop.yarn.server.api.protocolrecords.LogAggregationReport;
import org.apache.hadoop.yarn.server.api.records.LogAggregationStatus;

import com.google.protobuf.TextFormat;

@Private
@Unstable
public class LogAggregationReportPBImpl extends LogAggregationReport {

  LogAggregationReportProto proto = LogAggregationReportProto
    .getDefaultInstance();
  LogAggregationReportProto.Builder builder = null;
  boolean viaProto = false;

  private static final String LOGAGGREGATION_STATUS_PREFIX = "LOG_";

  private ApplicationId applicationId;
  private NodeId nodeId;

  public LogAggregationReportPBImpl() {
    builder = LogAggregationReportProto.newBuilder();
  }

  public LogAggregationReportPBImpl(LogAggregationReportProto proto) {
    this.proto = proto;
    viaProto = true;
  }

  public LogAggregationReportProto getProto() {
    mergeLocalToProto();
    proto = viaProto ? proto : builder.build();
    viaProto = true;
    return proto;
  }

  @Override
  public int hashCode() {
    return getProto().hashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == null)
      return false;
    if (other.getClass().isAssignableFrom(this.getClass())) {
      return this.getProto().equals(this.getClass().cast(other).getProto());
    }
    return false;
  }

  @Override
  public String toString() {
    return TextFormat.shortDebugString(getProto());
  }

  private void mergeLocalToBuilder() {
    if (this.applicationId != null
        && !((ApplicationIdPBImpl) this.applicationId).getProto().equals(
          builder.getApplicationId())) {
      builder.setApplicationId(convertToProtoFormat(this.applicationId));
    }

    if (this.nodeId != null
        && !((NodeIdPBImpl) this.nodeId).getProto().equals(
          builder.getNodeId())) {
      builder.setNodeId(convertToProtoFormat(this.nodeId));
    }
  }

  private void mergeLocalToProto() {
    if (viaProto)
      maybeInitBuilder();
    mergeLocalToBuilder();
    proto = builder.build();
    viaProto = true;
  }

  private void maybeInitBuilder() {
    if (viaProto || builder == null) {
      builder = LogAggregationReportProto.newBuilder(proto);
    }
    viaProto = false;
  }

  @Override
  public ApplicationId getApplicationId() {
    if (this.applicationId != null) {
      return this.applicationId;
    }

    LogAggregationReportProtoOrBuilder p = viaProto ? proto : builder;
    if (!p.hasApplicationId()) {
      return null;
    }
    this.applicationId = convertFromProtoFormat(p.getApplicationId());
    return this.applicationId;
  }

  @Override
  public void setApplicationId(ApplicationId appId) {
    maybeInitBuilder();
    if (appId == null)
      builder.clearApplicationId();
    this.applicationId = appId;
  }

  private ApplicationIdProto convertToProtoFormat(ApplicationId t) {
    return ((ApplicationIdPBImpl) t).getProto();
  }

  private ApplicationIdPBImpl convertFromProtoFormat(
      ApplicationIdProto applicationId) {
    return new ApplicationIdPBImpl(applicationId);
  }

  @Override
  public LogAggregationStatus getLogAggregationStatus() {
    LogAggregationReportProtoOrBuilder p = viaProto ? proto : builder;
    if (!p.hasLogAggregationStatus()) {
      return null;
    }
    return convertFromProtoFormat(p.getLogAggregationStatus());
  }

  @Override
  public void
      setLogAggregationStatus(LogAggregationStatus logAggregationStatus) {
    maybeInitBuilder();
    if (logAggregationStatus == null) {
      builder.clearLogAggregationStatus();
      return;
    }
    builder.setLogAggregationStatus(convertToProtoFormat(logAggregationStatus));
  }

  private LogAggregationStatus convertFromProtoFormat(
      LogAggregationStatusProto s) {
    return LogAggregationStatus.valueOf(s.name().replace(
      LOGAGGREGATION_STATUS_PREFIX, ""));
  }

  private LogAggregationStatusProto
      convertToProtoFormat(LogAggregationStatus s) {
    return LogAggregationStatusProto.valueOf(LOGAGGREGATION_STATUS_PREFIX
        + s.name());
  }

  @Override
  public String getDiagnosticMessage() {
    LogAggregationReportProtoOrBuilder p = viaProto ? proto : builder;
    if (!p.hasDiagnostics()) {
      return null;
    }
    return p.getDiagnostics();
  }

  @Override
  public void setDiagnosticMessage(String diagnosticMessage) {
    maybeInitBuilder();
    if (diagnosticMessage == null) {
      builder.clearDiagnostics();
      return;
    }
    builder.setDiagnostics(diagnosticMessage);
  }

  @Override
  public NodeId getNodeId() {
    if (this.nodeId != null) {
      return this.nodeId;
    }

    LogAggregationReportProtoOrBuilder p = viaProto ? proto : builder;
    if (!p.hasNodeId()) {
      return null;
    }
    this.nodeId = convertFromProtoFormat(p.getNodeId());
    return this.nodeId;
  }

  @Override
  public void setNodeId(NodeId nodeId) {
    maybeInitBuilder();
    if (nodeId == null)
      builder.clearNodeId();
    this.nodeId = nodeId;
  }

  private NodeIdProto convertToProtoFormat(NodeId t) {
    return ((NodeIdPBImpl) t).getProto();
  }

  private NodeIdPBImpl convertFromProtoFormat(NodeIdProto nodeId) {
    return new NodeIdPBImpl(nodeId);
  }
}
