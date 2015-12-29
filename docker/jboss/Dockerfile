FROM jboss/base-jdk:8

ENV JBOSS_VERSION 7.0.0.Beta
ENV JBOSS_HOME /opt/jboss/jboss-eap-7.0

COPY jboss-eap-$JBOSS_VERSION.zip $HOME

RUN cd $HOME \
	&& unzip jboss-eap-$JBOSS_VERSION.zip \
	&& rm jboss-eap-$JBOSS_VERSION.zip

ENV LAUNCH_JBOSS_IN_BACKGROUND true

# Add a user in administration realm
RUN /opt/jboss/jboss-eap-7.0/bin/add-user.sh admin Admin#007 --silent

EXPOSE 8080 9990

CMD ["/opt/jboss/jboss-eap-7.0/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]