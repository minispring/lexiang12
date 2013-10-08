package services

import securesocial.core._
import play.api.mvc.{Session, RequestHeader, Cookie}
import play.api.{Application, Logger}

/**
 * A sample event listener
 */
class MyEventListener(app: Application) extends EventListener {
  override def id: String = "my_event_listener"

  def onEvent(event: Event, request: RequestHeader, session: Session): Option[Session] = {
    val eventName = event match {
      case e: LoginEvent => Cookie("username", event.user.fullName)
      case e: LogoutEvent => session - "username"
      case e: SignUpEvent => session+("username" -> event.user.fullName)
      case e: PasswordResetEvent => "password reset"
      case e: PasswordChangeEvent => "password change"
    }
	
	println(request.cookies)
	//Logger.info("traced %s event for user %s".format(eventName, event.user.fullName))
    // Not changing the session so just return None
    // if you wanted to change the session then you'd do something like
    // Some(session + ("your_key" -> "your_value"))
    None
	
  }
}