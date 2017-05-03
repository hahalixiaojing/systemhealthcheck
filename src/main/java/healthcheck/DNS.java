package healthcheck;


import okhttp3.Dns;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DNS implements Dns {

    private IpListContainer container;

    public DNS(IpListContainer container) {
        this.container = container;
    }

    @Override
    public List<InetAddress> lookup(String s) throws UnknownHostException {
        if (s == null) {
            throw new UnknownHostException("s == null");
        }
        InetAddress[] allByName = InetAddress.getAllByName(this.container.ipList[this.container.index]);
        return Arrays.stream(allByName).collect(Collectors.toList());
    }
}
