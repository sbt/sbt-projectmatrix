import sbt.{Def, VirtualAxis}
import sbtprojectmatrix.ProjectMatrixPlugin.autoImport.virtualAxes

object AkkaAxis extends InnerClassAxis("akka")

object PlayAxis extends InnerClassAxis("play")

class InnerClassAxis(prefix: String) {

  case class Value(version: String) extends VirtualAxis.WeakAxis {

    private val majorMinor: String = version.split('.').take(2).mkString // e.g. 28

    def nameComponent: String = s"$prefix$majorMinor" // e.g. play28

    override def directorySuffix: String = s"-$nameComponent"

    override def idSuffix: String = directorySuffix

    override def toString: String = s"$nameComponent($version)"
  }

  def current = Def.setting {
    virtualAxes.value.collectFirst {
      case a: Value => a
    }.get
  }
}
